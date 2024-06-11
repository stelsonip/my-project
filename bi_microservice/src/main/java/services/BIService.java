/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import model.BI;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author stels
 */
public class BIService {
    //CRUD (CREATE & UPDATE)
    public boolean createBI(BI bi) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.saveOrUpdate(bi);

            session.flush();

            transaction.commit();

            session.clear();

            session.close();

            return true;

        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();

            }

            e.printStackTrace();

        }

        return false;

    }

    //CRUD(READ)
    //listar todos cidadãos
    @Transactional
    public List<BI> listarBI() {

        List<BI> lista = null;

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            lista = session.createNamedQuery("BI.findAll", BI.class).getResultList();

            session.clear();

            session.close();

        } catch (Exception e) {

            System.out.println("ERRO: " + e.getMessage());
            if (transaction != null) {

                transaction.rollback();

            }

            e.printStackTrace();

        }

        return lista;

    }

    //pegar pelo numero do BI
    public Optional<BI> bi(String numBI) {

        Optional<BI> bi = null;

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            bi = session.createNamedQuery("BI.findNB", BI.class)
                    .setParameter("nb", numBI)
                    .getResultList()
                    .stream()
                    .findFirst();

            session.flush();

            transaction.commit();

            session.clear();

            //session.close();
        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();

            }

            e.printStackTrace();

        }

        return bi;

    }

    //CRUD (DELETE)
    public boolean delete(BI bi) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.delete(bi);

            session.flush();

            transaction.commit();

            session.close();

            return true;

        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();

            }

            e.printStackTrace();

        }

        return false;

    }
}
