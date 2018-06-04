package dao

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence


class DBWorker{
    companion object {
        val factory: EntityManagerFactory
        val db: EntityManager;

        init {
            factory= Persistence.createEntityManagerFactory("myPU")
            db = factory.createEntityManager()
        }

        fun testMe(){
            println("is open: ${db.isOpen}")
        }
    }
}