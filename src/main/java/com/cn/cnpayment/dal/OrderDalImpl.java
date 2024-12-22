 package com.cn.cnpayment.dal;

 import com.cn.cnpayment.entity.Orders;
 import com.cn.cnpayment.exception.NotFoundException;
 import jakarta.persistence.EntityManager;
 import org.hibernate.Session;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;

 import java.util.List;

 /**
  Complete the OrderDalImpl implementation class as mentioned below:

  a. Autowire EntityManager.

  b. Override the following methods:

  1. getById(int id): This method fetches an Order from the database for a specific Id.

  2. save(Orders order): This method saves an Order into the database.

  3. delete(int id): This method deletes an Order from the database for a specific Id.

  4. getAllOrders(): This method fetches the list of Orders from the database.
  **/


@Repository
public class OrderDalImpl implements OrderDal {

 @Autowired
  EntityManager entityManager;
 @Override
 public Orders getById(int id) {
  Session session = entityManager.unwrap(Session.class);
  Orders orders = session.get(Orders.class,id);
  if(orders==null){
   throw new NotFoundException("not found");
  }
  return orders;
 }

 @Override
 public void save(Orders orders) {
 Session session = entityManager.unwrap(Session.class);
 session.save(orders);
 }

 @Override
 public void delete(int id) {
Session session = entityManager.unwrap(Session.class);
Orders order = session.get(Orders.class,id);
if(order==null){
 throw new NotFoundException("order not found");
}
session.delete(order);
 }

 @Override
 public List<Orders> getAllOrders() {
  Session session = entityManager.unwrap(Session.class);
 return session.createQuery("from Orders", Orders.class).getResultList();

 }
 //Autowire the EntityManager object.
}
