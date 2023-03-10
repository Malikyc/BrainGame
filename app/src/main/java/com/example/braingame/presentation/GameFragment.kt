package com.example.braingame.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.braingame.R
import com.example.braingame.databinding.FragmentGameBinding
import com.example.braingame.domain.entities.Level
import com.example.braingame.domain.entities.Question

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()


   private val viewModelFactory : GameViewModelFactory by lazy {
       GameViewModelFactory(requireActivity().application,args.level)
   }
    private val viewModel : GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory
        )[GameViewModel::class.java]
    }

    private var _binding : FragmentGameBinding? = null
    private val binding : FragmentGameBinding
        get() {
            return _binding ?: throw RuntimeException("FragmentGameBinding is null")
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        subscribeOnLiveData()


    }

    private fun subscribeOnLiveData(){
        with(viewModel) {
            gameResult.observe(viewLifecycleOwner){
                findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToGameFinishedFragment(it))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}