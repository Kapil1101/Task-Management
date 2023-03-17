package com.taskManager.repository;

import com.taskManager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Find a customer by ID
    Customer findById(long id);
    
    // Find all customers with a specific first name
    List<Customer> findByName(String firstName);
    
    // Find all customers with a specific last name
    
    // Find all customers with a specific email address
    List<Customer> findByEmail(String email);
    
    // Find all customers with a specific phone number
    List<Customer> findByPhoneNumber(String phoneNumber);
    
    // Add a new customer
    @Modifying
    @Query(value = "insert into customer (name, email, phone_number) values (:name, :email, :phoneNumber)", nativeQuery = true)
    void addCustomer(@Param("name") String name, @Param("email") String email, @Param("phoneNumber") String phoneNumber);
    
    // Update a customer's information
//    @Modifying
//    @Query(value = "update customer set first_name = :firstName, last_name = :lastName, email = :email, phone_number = :phoneNumber where id = :id", nativeQuery = true)
//    void updateCustomer(@Param("id") long id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("phoneNumber") String phoneNumber);
//
    // Delete a customer by ID
    @Modifying
    @Query(value = "delete from customer where id = :id", nativeQuery = true)
    void deleteCustomer(@Param("id") long id);
    
}
