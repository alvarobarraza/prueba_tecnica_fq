package com.example.technical_test_fq.view.projects.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technical_test_fq.R
import com.example.technical_test_fq.data.model.ResultProjectMetrics
import com.example.technical_test_fq.databinding.FragmentGraphProjectBinding
import com.example.technical_test_fq.databinding.FragmentListProjectsBinding
import com.example.technical_test_fq.domain.model.ResultEvent
import com.example.technical_test_fq.view.base.BaseFragment
import com.example.technical_test_fq.view.projects.viewModel.GraphProjectViewModel
import com.example.technical_test_fq.view.utils.GenericDialogFragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GraphProjectFragment : BaseFragment() {

    private lateinit var binding: FragmentGraphProjectBinding

    private val graphProjectViewModel: GraphProjectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGraphProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        graphProjectViewModel.metricsProjects()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initViewModel()

    }

    private fun initViewModel() {
        graphProjectViewModel.metricsProjectsData.observe(viewLifecycleOwner) { event ->
            when(event){
                is ResultEvent.Loading -> {
                    showLoading()
                }
                is ResultEvent.Success -> {
                    hideLoading()
                    setupPieChart(binding.pieChart,event.data)

                }
                is ResultEvent.ErrorCode -> {
                    hideLoading()
                    showGenericDialogNotification(
                        "Error de Conexión",
                        "Error con el codigo ${event.code}",
                        type = GenericDialogFragment.DialogType.ERROR,
                        positiveListener = {}
                    )
                }
                is ResultEvent.Error -> {
                    hideLoading()
                    showGenericDialogNotification(
                        "Error de Conexión",
                        "Error ${event.exception}",
                        type = GenericDialogFragment.DialogType.ERROR,
                        positiveListener = {}
                    )
                }

                else -> {
                    hideLoading()
                }
            }
        }
    }

    private fun setupPieChart(pieChart: PieChart, resultMetrics: ResultProjectMetrics) {
        val pieEntries = ArrayList<PieEntry>()
        pieEntries.add(PieEntry(resultMetrics.projectsByStatus.active.toFloat(), "Proyectos Activos"))
        pieEntries.add(PieEntry(resultMetrics.projectsByStatus.completed.toFloat(), "Proyectos Completados"))

        val dataSet = PieDataSet(pieEntries, "Estado de Proyectos")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        val data = PieData(dataSet)
        data.setValueTextSize(10f)
        data.setValueTextColor(Color.WHITE)

        pieChart.data = data
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.setDrawCenterText(true)
        pieChart.centerText = "Estado de Proyectos"
        pieChart.setEntryLabelColor(Color.BLACK)

        val legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)

        pieChart.invalidate()
    }

    private fun initListener() {
        with(binding){

            backPressed()

            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_graphProjectFragment_to_listProjectsFragment)
            }

        }
    }

    private fun backPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_graphProjectFragment_to_listProjectsFragment)
            }
        })
    }

}