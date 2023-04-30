package tdtu.edu.vn.finalproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.finalproject.Model.Receipt;
import tdtu.edu.vn.finalproject.Model.User;
import tdtu.edu.vn.finalproject.Repository.AdminOrderRpository;
import tdtu.edu.vn.finalproject.Repository.AdminRepository;


import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminOrderRpository adminOrderRpository;

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
    public List<User> ListAllUser(String keyword)
    {
        if(keyword != null)
        {
            return adminRepository.search(keyword);
        }
        return adminRepository.findAll();
    }

    @Override
    public List<Receipt> ListAllReceipt() {
        return adminOrderRpository.findAll();
    }

    @Override
    public void deleteOrderById(String id) {
        adminOrderRpository.deleteById(id);
    }

    @Override
    public Receipt saveReceipt(Receipt receipt) {
        return adminOrderRpository.save(receipt);
    }

    @Override
    public Receipt findOrderById(String id) {
        return adminOrderRpository.findById(id).get();
    }

    public List<String> getAllRoles(){
        if(adminRepository.getAllRole().isEmpty()){
            return List.of("admin","customer");
        }
        return adminRepository.getAllRole();
    }

    public List<String> getStatusPay(){
        return List.of("Unpaid","Paid");
    }




}
