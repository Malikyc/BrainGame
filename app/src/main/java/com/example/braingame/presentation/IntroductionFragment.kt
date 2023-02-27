package com.example.braingame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.braingame.R
import com.example.braingame.databinding.FragmentGameBinding
import com.example.braingame.databinding.FragmentIntroBinding

class IntroductionFragment : Fragment() {
    private var _binding : FragmentIntroBinding? = null
    private val binding : FragmentIntroBinding
        get() {
            return _binding ?: throw RuntimeException("FragmentIntroBinding is null")
        }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIntroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
             findNavController().navigate(R.id.action_introductionFragment_to_chooseLevelFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}