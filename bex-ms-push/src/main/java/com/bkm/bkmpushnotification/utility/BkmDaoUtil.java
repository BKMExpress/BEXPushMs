package com.bkm.bkmpushnotification.utility;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 15.02.2018.
 */
public class BkmDaoUtil {

    /**
     * @param list
     * @return Optional based generic
     * */
    public static <T> Optional<T> findFirst(List<T> list) {
        Optional<List<T>> optionalList = Optional.ofNullable(list);
        if (optionalList.isPresent()) {
            return optionalList.get().stream().findFirst();
        }
        return Optional.empty();
    }
}
