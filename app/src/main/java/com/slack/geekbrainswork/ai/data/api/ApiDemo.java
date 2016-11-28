package com.slack.geekbrainswork.ai.data.api;

import android.util.Log;

import com.slack.geekbrainswork.ai.data.dto.IsBusyResponse;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Response;

public class ApiDemo {

    private static ApiDemo api;
    private static List<SiteDTO> siteDTOList = new ArrayList<>();
    private static List<UserDTO> userDTOList = new ArrayList<>();
    private static String token = "sa4564asdas4f56af4asasewqjmwer24";

    private ApiDemo() {
        siteDTOList.add(new SiteDTO(1, "lenta.ru"));
        siteDTOList.add(new SiteDTO(2, "echo.msk.ru"));

        userDTOList.add(new UserDTO(1, "Andrey", "andrey@mail.ru", 1));
        userDTOList.add(new UserDTO(3, "Michailov", "michailov@gmail.com", 2));
        userDTOList.add(new UserDTO(4, "Alex", "alex@yandex.ru", 1));
    }

    public static ApiDemo getApi() {
        if (api == null) {
            api = new ApiDemo();
        }
        return api;
    }

    public List<SiteDTO> getSiteDTOList() {
        Log.d("GET ", Thread.currentThread().getName());
        return siteDTOList;
    }

    public SiteDTO updateSiteDTO(SiteDTO site) {
        Log.d("UPDATE ", Thread.currentThread().getName());
        for (int i = 0; i < siteDTOList.size(); i++) {
            if (siteDTOList.get(i).getId().intValue() == site.getId().intValue()) {
                siteDTOList.get(i).setName(site.getName());
                return siteDTOList.get(i);
            }
        }
        return null;
    }

    public SiteDTO createSiteDTO(SiteDTO siteDTO) {
        Log.d("CREATE ", Thread.currentThread().getName());
        SiteDTO newSiteDTO = new SiteDTO(getMaxId() + 1, siteDTO.getName());
        siteDTOList.add(newSiteDTO);
        return siteDTO;
    }

    private Integer getMaxId() {
        Integer maxId = 0;
        Integer curId;
        for (SiteDTO siteDTO : siteDTOList) {
            curId = siteDTO.getId();
            if (curId > maxId) {
                maxId = curId;
            }
        }
        return maxId;
    }

    public Void deleteSiteDTO(int id) {
        Log.d("REMOVE ", Thread.currentThread().getName());
        for (Iterator<SiteDTO> iterator = siteDTOList.iterator(); iterator.hasNext(); ) {
            SiteDTO siteDTO = iterator.next();
            if (siteDTO.getId() == id) {
                iterator.remove();
            }
        }
        return null;
    }

    public TokenResponse auth(String login, String password) {
        // Simulate network access.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new TokenResponse(token);
    }

    public List<UserDTO> getUserDTOList() {
        Log.d("GET ", Thread.currentThread().getName());
        return userDTOList;
    }


    public UserDTO updateUserDTO(Integer id, String pass) {
        Log.d("UPDATE ", Thread.currentThread().getName());
        for (int i = 0; i < userDTOList.size(); i++) {
            if (userDTOList.get(i).getId().intValue() == id) {
                //userDTOList.get(i).(site.getName());
                return userDTOList.get(i);
            }
        }
        return null;
    }

    public Void deleteUserDTO(int id) {
        Log.d("REMOVE ", Thread.currentThread().getName());
        for (Iterator<UserDTO> iterator = userDTOList.iterator(); iterator.hasNext(); ) {
            UserDTO userDTO = iterator.next();
            if (userDTO.getId() == id) {
                iterator.remove();
            }
        }
        return null;
    }

    public UserDTO createUserDTO(UserDTO userDTO) {
        Log.d("CREATE ", Thread.currentThread().getName());
        UserDTO newUserDTO = new UserDTO(getMaxId() + 1, userDTO.getName(), userDTO.getEmail(), userDTO.getPrivilege());
        userDTOList.add(newUserDTO);
        return newUserDTO;
    }

    public Response<Void> checkLogin(String login) {
        for (int i = 0; i < userDTOList.size(); i++) {
            if (userDTOList.get(i).getName().equals(login)) {
                return Response.error(409, new ResponseBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public BufferedSource source() {
                        return null;
                    }
                });
            }
        }
        return Response.success(null);
    }

    public Response<Void> checkEmail(String email) {
        for (int i = 0; i < userDTOList.size(); i++) {
            if (userDTOList.get(i).getEmail().equals(email)) {
                return Response.error(409, new ResponseBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public BufferedSource source() {
                        return null;
                    }
                });
            }
        }
        return Response.success(null);
    }
}
