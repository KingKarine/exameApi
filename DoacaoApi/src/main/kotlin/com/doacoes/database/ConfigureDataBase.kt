package com.doacoes.database

import com.doacoes.features.doacao.entities.DonationTable
import com.doacoes.features.user.entities.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction


fun configureDataBase() {
    initDB()
    transaction {
        addLogger(StdOutSqlLogger)
        create(
            // New
            UserTable,
            DonationTable
        )
    }
}

private fun initDB() {
    // database connection is handled from hikari properties
    //val config = HikariConfig("/hikari.properties")
    //val dataSource = HikariDataSource(config)
    //runFlyway(dataSource)
    Database.connect("jdbc:sqlite:data/data.db", "org.sqlite.JDBC")
}

