package rikkei.academy.business.design;

import rikkei.academy.business.model.User;

public interface IUserService extends IGenericService<User,Integer> {
        void registerUser(User newUser);
}
