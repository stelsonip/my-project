/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.HashMap;
import java.util.Map;
import model.BI;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

/**
 *
 * @author stels
 */
public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {

                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                Map<String, Object> settings = new HashMap<>();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost/id_registration?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Africa/Luanda");

                settings.put(Environment.USER, "apiuser");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.SHOW_SQL, true);

                // HikariCP settings
                // Maximum waiting time for a connection from the pool
                settings.put("hibernate.hikari.connectionTimeout", "20000");
                // Minimum number of ideal connections in the pool
                settings.put("hibernate.hikari.minimumIdle", "10");
                // Maximum number of actual connection in the pool
                settings.put("hibernate.hikari.maximumPoolSize", "20");
                // Maximum time that a connection is allowed to sit ideal in the pool
                settings.put("hibernate.hikari.idleTimeout", "300000");

                registryBuilder.applySettings(settings);

                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(BI.class);

                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void closeConection() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
