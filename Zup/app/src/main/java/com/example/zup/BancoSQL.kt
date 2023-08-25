package com.example.zup

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class BancoSQL {

    fun connectToDatabase(): Connection? {
        val serverName = "projeto-zup.database.windows.net"
        val databaseName = "bd-projeto-zup"
        val username = "admin-zup"
        val password = "#Gfgrupo5"

        return try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
            val connectionUrl = "jdbc:sqlserver://$serverName;database=$databaseName;user=$username;password=$password;"
            DriverManager.getConnection(connectionUrl)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            null
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }
}
