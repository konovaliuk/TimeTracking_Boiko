package services;

import dao.DaoFactory;
import dao.interfaces.UserDao;
import dao.interfaces.UserTypeDao;
import entities.User;
import org.apache.log4j.Logger;
import services.interfaces.RegistrationService;
import services.interfaces.Service;

public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

    private static RegistrationServiceImpl instance;

    public static RegistrationServiceImpl getInstance(){
        if(instance == null){
            instance = new RegistrationServiceImpl();
        }
        return instance;
    }

    private RegistrationServiceImpl(){}

    @Override
    public boolean performRegistration(User user){
        UserTypeDao userTypeDao = DaoFactory.createUserTypeDao();
        try{
            user.setUserTypeId(userTypeDao.findWhereTypeNameEquals("Client").getUserTypeId());
            UserDao userDao = DaoFactory.createUserDao();
            if(userDao.findWhereEmailEquals(user.getEmail())== null){
                userDao.insertNewUser(user);
                return true;
            }
        }catch (Exception e){
            LOGGER.error("Exception in performRegistration method.");
        }
        return false;
    }
}