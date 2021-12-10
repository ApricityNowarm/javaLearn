package com.smbms.service.provider;

import com.smbms.bean.Provider;

import java.util.List;

public interface ProviderService {
    boolean addProvider(Provider pro);
    boolean delProvider(int proId);
    boolean updateProvider(Provider pro);
    Provider getProviderById(int proId);
    Provider getProviderByCode(String proCode);
    List<Provider> getProviderList(String proCode, String proName, int startIndex, int pageSize);
    List<Provider> getAllProviderList();
    int getProviderCount(String proCode,String proName);
}
