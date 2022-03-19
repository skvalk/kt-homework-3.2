import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateCommission() {

        val card = TypeCard.MIR
        val amount = 10_000_00
        val previousBuyingSum = 300_00

        val result = calculateCommission(
            amount = amount,
            previousBuyingSum = previousBuyingSum,
            typeOfCard = card
        )

        assertEquals(7500, result)
    }

    @Test
    fun limits() {

        val card = TypeCard.MIR
        val amount = 10_000_00
        val previousBuyingSum = 300_00

        val result = limits(
            amount = amount,
            previousBuyingSum = previousBuyingSum,
            typeOfCard = card
        )

        assertEquals("Перевод возможен", result)
    }
}