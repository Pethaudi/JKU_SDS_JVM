package dao

import entities.Appearance
import entities.Pokemon
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence


class DbWorker{
    companion object {
        val factory: EntityManagerFactory
        val db: EntityManager;

        init {
            factory= Persistence.createEntityManagerFactory("myPU")
            db = factory.createEntityManager()
        }

        fun add(obj: Any){
            db.transaction.begin()
            db.persist(obj)
            db.transaction.commit()
        }

        fun add(p: Pokemon){
            println(p)
            db.transaction.begin()
            db.persist(p)
            db.transaction.commit()
        }

        fun add(p: Appearance){
            println(p)
            db.transaction.begin()
            db.persist(p)
            db.transaction.commit()
        }

        fun add(p: List<Appearance>){
            db.transaction.begin()
            val transaction = db.transaction

            for(i in 0 until p.count()){
                println("------------- new item ${i%25} -------------")
                if(i > 0 && i % 25 == 0){
                    transaction.commit()
                    transaction.begin()
                    println("++++++++ ${transaction.isActive} ++++++++")

                    db.clear()
                }

                db.merge(p[i])
            }
            transaction.commit()
        }
    }
}