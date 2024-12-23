package com.webclothes.webclothesservice.service.customer;

import com.webclothes.webclothesservice.exception.InternalServerException;
import com.webclothes.webclothesservice.exception.ResourceNotFoundException;
import com.webclothes.webclothesservice.model.Customer;
import com.webclothes.webclothesservice.model.User;
import com.webclothes.webclothesservice.repository.CustomerRepository;
import com.webclothes.webclothesservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long userId) {
        return Optional.ofNullable(customerRepository.findByUser_Id(userId).get());
    }

    public Customer saveOrUpdateCustomer(Long userId, String firstName,String lastName, String phoneNumber, String address, MultipartFile file) throws IOException, SQLException {
        User user = userRepository.findById(userId).orElse(null);
        Customer customer = new Customer();
        if (user != null) {
            if(!file.isEmpty()){
                byte[] photoBytes = file.getBytes();
                Blob photoBlob = new SerialBlob(photoBytes);
                customer.setAvatar(photoBlob);
            }
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);
            customer.setUser(user);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Customer updateCustomer(Long id, String firstName,String lastName, String phoneNumber, String address, byte[] photoBytes) {
        Customer customer;

        // Kiểm tra xem khách hàng đã tồn tại chưa
        Optional<Customer> existingCustomerOpt = customerRepository.findByUser_Id(id);

        if (existingCustomerOpt.isPresent()) {
            // Nếu đã tồn tại, lấy khách hàng hiện tại
            customer = existingCustomerOpt.get();
            // Cập nhật thông tin nếu các giá trị mới không null
            if (firstName != null) customer.setFirstName(firstName);
            if (lastName != null) customer.setLastName(lastName);
            if (phoneNumber != null) customer.setPhoneNumber(phoneNumber);
            if (address != null) customer.setAddress(address);
            if (photoBytes != null && photoBytes.length > 0) {
                try {
                    customer.setAvatar(new SerialBlob(photoBytes));
                } catch (SQLException ex) {
                    throw new InternalServerException("Error updating customer");
                }
            }
        } else {
            // Nếu chưa tồn tại, tạo một khách hàng mới với các giá trị đã nhập
            customer = new Customer();
            customer.setUser(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));
            if (firstName != null && !firstName.trim().isEmpty()) {
                customer.setFirstName(firstName);
            } else {
                customer.setFirstName("");
            }

            if (lastName != null && !lastName.trim().isEmpty()) {
                customer.setLastName(lastName);
            } else {
                customer.setLastName("");
            }

            if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                customer.setPhoneNumber(phoneNumber);
            } else {
                customer.setPhoneNumber("");
            }

            if (address != null && !address.trim().isEmpty()) {
                customer.setAddress(address);
            } else {
                customer.setAddress("");
            }

            if (photoBytes != null && photoBytes.length > 0) {
                try {
                    customer.setAvatar(new SerialBlob(photoBytes));
                } catch (SQLException ex) {
                    throw new InternalServerException("Error creating customer");
                }
            } else {
                try {
                    byte[] defaultAvatar = getDefaultAvatarBytes();
                    customer.setAvatar(new SerialBlob(defaultAvatar));
                } catch (SQLException ex) {
                    throw new InternalServerException("Error setting default avatar");
                }
            }
        }
        return customerRepository.save(customer);
    }

    public static byte[] getDefaultAvatarBytes() {
        try {
            Path path = Paths.get("src/main/resources/aokhoaclen.png");
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Error loading default avatar", e);
        }
    }

    @Override
    public byte[] getAvatarById(Long customerId) throws SQLException {
        Optional<Customer> theCustomer = customerRepository.findByUser_Id(customerId);
        if(theCustomer.isEmpty()){
            throw new ResourceNotFoundException("Sorry, Customer not found!");
        }
        Blob photoCustomer = theCustomer.get().getAvatar();
        if (photoCustomer != null) {
            try {
                return photoCustomer.getBytes(1, (int) photoCustomer.length());
            } catch (SQLException e) {
                throw new SQLException("Error reading Blob data", e);
            }
        }

        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
