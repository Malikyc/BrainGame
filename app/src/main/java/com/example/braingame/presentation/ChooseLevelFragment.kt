package com.example.braingame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.braingame.R
import com.example.braingame.databinding.FragmentChooseLevelBinding
import com.example.braingame.domain.entities.Level

class ChooseLevelFragment : Fragment() {

    private var _binding : FragmentChooseLevelBinding? = null
    private val binding : FragmentChooseLevelBinding
    get() {
     return _binding ?: throw RuntimeException("FragmentChooseLevelBinding is null")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnButtonClickListners()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnButtonClickListners(){
        with(binding){
        buttonLevelTest.setOnClickListener {
            lauchGameFragment(Level.TEST)
        }
        buttonLevelEasy.setOnClickListener {
            lauchGameFragment(Level.EASY)
        }
        buttonLevelNormal.setOnClickListener {
            lauchGameFragment(Level.NORMAL)
        }
        buttonLevelHard.setOnClickListener {
            lauchGameFragment(Level.HARD)
        }
        }
    }

    private fun lauchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )

    }


}