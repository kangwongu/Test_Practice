package com.testcafekiosk.integration

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestComponent
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

@TestComponent
class DBInitializer {

    private val tableNames = mutableListOf<String>()

    @Autowired
    private lateinit var dataSource: DataSource

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private fun findDatabaseTableNames() {
        dataSource.connection.use { connection ->
            connection.createStatement().use { statement ->
                val resultSet = statement.executeQuery("SHOW TABLES")
                while (resultSet.next()) {
                    val tableName = resultSet.getString(COLUMN_INDEX)
                    tableNames.add(tableName)
                }
            }
        }
    }

    private fun truncate() {
        setForeignKeyCheck(OFF)
        tableNames.forEach { tableName ->
            entityManager.createNativeQuery("TRUNCATE TABLE $tableName").executeUpdate()
        }
        setForeignKeyCheck(ON)
    }

    private fun setForeignKeyCheck(mode: Int) {
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = $mode").executeUpdate()
    }

    @Transactional
    fun clear() {
        if (tableNames.isEmpty()) {
            findDatabaseTableNames()
        }
        entityManager.clear()
        truncate()
    }

    companion object {
        private const val OFF = 0
        private const val ON = 1
        private const val COLUMN_INDEX = 1
    }
}