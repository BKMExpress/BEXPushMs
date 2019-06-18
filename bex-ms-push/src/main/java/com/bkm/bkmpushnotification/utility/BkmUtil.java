package com.bkm.bkmpushnotification.utility;

import com.bkm.bkmpushnotification.core.domain.PushNotificationInfo;
import com.bkm.bkmpushnotification.core.domain.PushNotificationRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Akif Hatipoglu on 22.02.2018.
 */
public class BkmUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BkmUtil.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final SecureRandom random = new SecureRandom();
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    private static       String hostIp;
    private static Long sequence = 0L ;

    /**
     * @param list
     * @return boolean
     * */
    public static <T> boolean isNullOrEmptyList(List<T> list) {
        return CollectionUtils.isEmpty(list);
    }

    /**
     * @param value
     * @return boolean
     * */
    public static boolean isNullOrEmptyString(String value) {
        return (value == null || value.equalsIgnoreCase(""));
    }

    /**
     * year month day hour second millisecond 2 digit random number
     * @return String
     * */
    public static String createId() {
        LocalDateTime currentTime = LocalDateTime.now();

        synchronized (sequence) {
            sequence = sequence + 1;
            if ( sequence == 10000000)
                sequence = 0L;
        }

        String numericPart = String.format("%07d", sequence );

        return currentTime.format(formatter).toString().concat(numericPart);
    }

    /**
     * @param apiKey
     * @param request
     * @return HttpEntity
     * */
    public static HttpEntity<String> getEntityForRestTemplate(Object request, String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        headers.set("Authorization", "key= " + apiKey);
        String requestJson = gson.toJson(request);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        return entity;
    }

    /**
     * @param message
     * @return PushNotificationInfo body string
     * */
    public static Optional<String> getMessageFromJson(String message) {
        Optional<String> optionalMessage = Optional.ofNullable(message);
        if (optionalMessage.isPresent()) {
            PushNotificationRequest object = gson.fromJson(message, PushNotificationRequest.class);
            if (object != null && object.getNotification()!=null) {
                return Optional.of(object.getNotification().getBody());
            }
        }
        return Optional.empty();
    }

    public static String getJsonString(Object obj){
        return gson.toJson(obj);
    }

    /**
     * Convert date to db pk id column
     * @return String
     * */
    public static String getIdForQueryByDate(){
        Date createDate = BkmUtil.addToNow(Calendar.MONTH, -1 * BkmConstants.HALF_YEAR_OF_MONTH);
        LocalDateTime currentTime = convertToLocalDateTimeViaInstant(createDate);
        return currentTime.format(formatter).toString().concat("00");
    }

    /**
     * @param date
     * @return boolean
     * */
    public static boolean isLastHalfYear(Date date) {
        if (date.compareTo(addToNow(Calendar.MONTH, -1 * BkmConstants.HALF_YEAR_OF_MONTH)) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * @param field => sample -> Calendar.MONTH
     * @param value
     * @return Date
     * */
    public static Date addToNow(int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, value);
        return calendar.getTime();
    }

    /**
     * @param dateToConvert
     * @return LocalDateTime
     * */
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * @return Host Ip String
     * */
    public static String getHostIp() {
        if(isNullOrEmptyString(hostIp)) {
            logger.debug("host ip is null/empty, trying to gather it");
            try {
                hostIp = "";
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface current = interfaces.nextElement();
                    if (!current.isUp() || current.isLoopback() || current.isVirtual()) {
                        continue;
                    }

                    Enumeration<InetAddress> addresses = current.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress currentAddress = addresses.nextElement();
                        if (currentAddress.isLoopbackAddress()) {
                            continue;
                        }

                        if (currentAddress instanceof Inet4Address) {
                            hostIp = currentAddress.getHostAddress();
                            //logger.debug("found value for host as {}",hostIp);
                            break;
                        }
                    }
                    if(!isNullOrEmptyString(hostIp)) {
                        break;
                    }
                }
            } catch (SocketException e) {
                logger.error("exception thrown when setting host ip : {}", e.getLocalizedMessage());
            }
        } else {
            logger.info("using already found value as host id {}",hostIp);
        }
        return hostIp;
    }
}
