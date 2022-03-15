const val MIN_COMMISSION = 35_00
const val MIN_TRANSFER_WITHOUT_COMMISSION = 300_00
const val MAX_TRANSFER_WITHOUT_COMMISSION = 75_000_00

enum class TypeCard {
    VK_PAY, MASTERCARD, VISA, MAESTRO, MIR
}

fun main() {
    val card = TypeCard.MIR
    val amount = 10_000_00
    val previousBuyingSum = 300_00
    println(limits(amount, previousBuyingSum, card))
    println("Комиссия составляет: ${calculateCommission(amount, previousBuyingSum, card)} копеек.")
}

fun calculateCommission(
    amount: Int,
    previousBuyingSum: Int = 0,
    typeOfCard: TypeCard = TypeCard.VK_PAY
): Int = when (typeOfCard) {
    TypeCard.VK_PAY -> 0
    TypeCard.VISA, TypeCard.MIR -> {
        val commission = amount * 0.0075
        if (commission < MIN_COMMISSION) MIN_COMMISSION else commission.toInt()
    }
    TypeCard.MAESTRO, TypeCard.MASTERCARD -> {
        val commission = amount * 0.006 + 20_00
        if (previousBuyingSum in MIN_TRANSFER_WITHOUT_COMMISSION..
            MAX_TRANSFER_WITHOUT_COMMISSION
        ) 0
        else commission.toInt()
    }
}

fun limits(
    amount: Int,
    previousBuyingSum: Int,
    typeOfCard: TypeCard,
): String {
    val transferInMonth = previousBuyingSum + amount
    return when (typeOfCard) {
        TypeCard.VK_PAY -> {
            val maxSumTransferInDay = 15_000_00
            val maxSumTransferInMonth = 40_000_00
            if (maxSumTransferInDay > amount ||
                maxSumTransferInMonth > transferInMonth
            ) "Перевод возможен"
            else "Перевод не возможен"
        }
        TypeCard.VISA, TypeCard.MIR, TypeCard.MAESTRO, TypeCard.MASTERCARD -> {
            val maxSumTransferInDay = 150_000_00
            val maxSumTransferInMonth = 600_000_00
            if (maxSumTransferInDay > amount ||
                maxSumTransferInMonth > transferInMonth
            ) "Перевод возможен"
            else "Перевод не возможен"
        }
    }
}
