package dao

import javax.persistence.EntityManager
import javax.persistence.Persistence


class DBWorker{
    companion object {
        val factory = Persistence.createEntityManagerFactory("myPU")
        val db: EntityManager;

        init {
            db = factory.createEntityManager()
        }

        fun testMe(){
            println("is open: ${db.isOpen}")
        }
    }
}