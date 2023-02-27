package com.example.braingame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.braingame.R
import com.example.braingame.databinding.FragmentGameFinishedBinding
import com.example.braingame.domain.entities.GameResult

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()


    private var _binding : FragmentGameFinishedBinding? = null
    private val binding : FragmentGameFinishedBinding
        get() {
            return _binding ?: throw RuntimeException("FragmentGameFinishedBinding is null")
        }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressButton()
        setOnRetryButton()
        binding.gameResult = args.gameResults
    }




    private fun setOnBackPressButton() {

    }

    private fun setOnRetryButton() {
        binding.buttonRetry.setOnClickListener {
            onRetry()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun onRetry(){
        findNavController().popBackStack()
    }

}