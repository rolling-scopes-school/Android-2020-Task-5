package ng_designs.android_2021_task_5.presentation.screens.main


import android.content.res.Configuration.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ng_designs.android_2021_task_5.databinding.MainFragmentBinding
import ng_designs.android_2021_task_5.domain.cat.models.Cats
import ng_designs.android_2021_task_5.presentation.adapters.paging_adapter.CatsPagingAdapter

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var binding: MainFragmentBinding? = null
    private val adapter: CatsPagingAdapter? get() = views { catsRecyclerView.adapter as? CatsPagingAdapter }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        (activity as AppCompatActivity).setSupportActionBar(binding?.mainFragmentToolbar)
        setHasOptionsMenu(true)

        views{
            catsRecyclerView.adapter = CatsPagingAdapter()

            if(resources.configuration.orientation == ORIENTATION_LANDSCAPE){
                (catsRecyclerView.layoutManager as GridLayoutManager).spanCount = 3
            } else if(resources.configuration.orientation == ORIENTATION_PORTRAIT){
                (catsRecyclerView.layoutManager as GridLayoutManager).spanCount = 2
            }
        }
        viewModel.cats.onEach(::submitImages).launchIn(lifecycleScope)
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.toolbar_menu, menu)
//        Log.i("MENU", "onCreateOptionsMenu reached!")
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Log.i("MENU", "onOptionsItemSelected reached!")
//        when (item.itemId) {
//            R.id.btn_exit -> {
//                activity?.finish()
//                exitProcess(0)
//            }
//            R.id.btn_settings -> {
////                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    @Suppress("UNCHECKED_CAST")
    private fun submitImages(cats: Any?) {
        lifecycleScope.launchWhenCreated {
            adapter?.submitData(cats as PagingData<Cats>)
        }
    }

    private fun <T> views(block: MainFragmentBinding.() -> T): T? = binding?.block()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}