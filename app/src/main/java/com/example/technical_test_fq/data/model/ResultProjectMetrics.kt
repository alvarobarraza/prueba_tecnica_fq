package com.example.technical_test_fq.data.model

data class ResultProjectMetrics (
    val totalProjects: Int,
    val projectsByStatus: ProjectsByStatus,
    val budgetByProject: List<BudgetByProject>
)

data class ProjectsByStatus(
    val active: Int,
    val completed: Int
)

data class BudgetByProject(
    val name: String,
    val value: Double
)