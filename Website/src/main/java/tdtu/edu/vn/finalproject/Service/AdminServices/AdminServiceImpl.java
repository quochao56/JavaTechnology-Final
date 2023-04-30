package tdtu.edu.vn.finalproject.Service.AdminServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Receipt;
import tdtu.edu.vn.finalproject.Model.User;
import tdtu.edu.vn.finalproject.Repository.AdminOrderRepository;
import tdtu.edu.vn.finalproject.Repository.AdminRepository;
import tdtu.edu.vn.finalproject.Service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminOrderRepository adminOrderRepository;

    @Override
    public List<User> getAllUser() {
        return adminRepository.findAll();
    }

    @Override
    public User save(User user) {
        return adminRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        adminRepository.deleteById(id);
    }

    @Override
    public User findByID(String id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public List<User> ListAllUser(String keyword) {
        if (keyword != null) {
            return adminRepository.search(keyword);
        }
        return adminRepository.findAll();
    }

    @Override
    public List<Receipt> ListAllReceipt() {
        return adminOrderRepository.findAll();
    }

    @Override
    public void deleteOrderById(String id) {
        adminOrderRepository.deleteById(id);
    }

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        return adminOrderRepository.save(receipt);
    }

    @Override
    public Receipt findOrderById(String id) {
        return adminOrderRepository.findById(id).get();
    }

    public List<String> getAllRoles() {
        if (adminRepository.getAllRole().isEmpty()) {
            return List.of("admin", "customer");
        }
        return adminRepository.getAllRole();
    }

    public List<String> getStatusPay() {
        return List.of("Unpaid", "Paid");
    }

    @Override
    public Page<User> paginationUser(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public Page<Receipt> paginationOrder(Pageable pageable) {
        return adminOrderRepository.findAll(pageable);
    }
}
