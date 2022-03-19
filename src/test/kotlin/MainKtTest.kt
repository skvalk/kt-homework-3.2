import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateCommission() {
        val amount = 10_000_00
        val previousBuyingSum = 300_00
        for (card in TypeCard.values()) {
            val result = calculateCommission(
                amount = amount,
                previousBuyingSum = previousBuyingSum,
                typeOfCard = card
            )
            println("$card $result")

            val hope = when (card) {
                TypeCard.VK_PAY -> 0
                TypeCard.VISA, TypeCard.MIR -> 7500
                TypeCard.MAESTRO, TypeCard.MASTERCARD -> 0
            }
            assertEquals(hope, result)
        }
        val result = calculateCommission(amount)
        assertEquals(0, result)

    }

    @Test
    fun calculateWithCommission(){

        val result = calculateCommission(
            amount = 100_000_00,
            previousBuyingSum = 76_000_00,
            typeOfCard = TypeCard.MASTERCARD
        )
        println(result)
        assertEquals(62000,result)
    }

    @Test
    fun limits() {
        val amount = 10_000_00
        val previousBuyingSum = 300_00
        for (card in TypeCard.values()) {
            val result = limits(
                amount = amount,
                previousBuyingSum = previousBuyingSum,
                typeOfCard = card
            )
            println(result)
            assertEquals("Перевод возможен", result)
        }

        for (card in TypeCard.values()) {
            val result = limits(
                amount = 150_000_00,
                previousBuyingSum = 690_000_00,
                typeOfCard = card
            )
            println(result)
            assertEquals("Перевод не возможен", result)
        }
    }
}