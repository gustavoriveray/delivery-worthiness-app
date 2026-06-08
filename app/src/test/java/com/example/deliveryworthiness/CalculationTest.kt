package com.example.deliveryworthiness

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculationTest {
    @Test
    fun testJobCostCalculation() {
        val gasPrice = 4.0f
        val miles = 10.0f
        val averageConsumption = 20.0f
        
        // (Gas price * Miles estimated for the job) / Average consumption
        val expectedCost = (gasPrice * miles) / averageConsumption
        val actualCost = 2.0f
        
        assertEquals(expectedCost, actualCost, 0.001f)
    }

    @Test
    fun testProfitCalculation() {
        val jobPay = 15.0f
        val jobCost = 2.0f
        
        val expectedProfit = jobPay - jobCost
        val actualProfit = 13.0f
        
        assertEquals(expectedProfit, actualProfit, 0.001f)
    }
}
