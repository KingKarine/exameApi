package com.doacoes.features.doacao.entities

import com.doacoes.features.doacao.models.DonationDtoRes
import com.doacoes.shared.entities.BaseIntEntity
import com.doacoes.shared.entities.BaseIntEntityClass
import com.doacoes.shared.entities.BaseIntTable
import org.jetbrains.exposed.dao.id.EntityID

object DonationTable : BaseIntTable("donations") {
    val title = varchar("title", 50)
    val quantity = integer("quantity")
    val expirationDate = varchar("exp_date", 100)
    val address = varchar("address", 100)
    val contacto = varchar("contacto", 100)
    val userId = integer("user_id")
}

class DonationEntity(id: EntityID<Int>) : BaseIntEntity(id, DonationTable) {
    companion object : BaseIntEntityClass<DonationEntity>(DonationTable)
    var title by DonationTable.title
    var quantity by DonationTable.quantity
    var address by DonationTable.address
    var expirationDate by DonationTable.expirationDate
    var contacto by DonationTable.contacto
    var userId by DonationTable.userId

    fun response() = DonationDtoRes(
        id = id.value,
        title = title,
        address = address,
        quantity = quantity,
        expirationDate = expirationDate,
        contato = contacto
    )

}
