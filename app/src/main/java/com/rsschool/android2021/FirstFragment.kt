package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var listener: ActionListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listener = context as ActionListener
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...
        val min = view.findViewById<EditText>(R.id.min_value)
        val max = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment
            val min = min.text.toString().toIntOrNull() ?: -1
            val max = max.text.toString().toIntOrNull() ?: -1
            if (min < 0 || max < 0) {
                val warning = Toast.makeText(activity as MainActivity, TEXT1, Toast.LENGTH_SHORT)
                warning.show()
            } else if (min > max)
            {
                val warning = Toast.makeText(activity as MainActivity, TEXT2, Toast.LENGTH_SHORT)
                warning.show()
            }
            else {
                listener.ActionSecondFragment(min, max)
            }

        }

    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
        private const val TEXT1 = "Invalid numbers"
        private const val TEXT2 = "ERROR: min>max"
    }
}