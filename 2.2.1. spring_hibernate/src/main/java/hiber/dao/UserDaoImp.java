package hiber.dao;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @SuppressWarnings("unchecked")
   public User getUserByCar(String model, int series) {

      String hq = "FROM User us WHERE us.car.model =:model and us.car.series =:series";
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hq);
      query.setParameter("model", model).setParameter("series", series);
      return query.getSingleResult();
   }


}
