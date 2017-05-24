package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

/**
 * Created by kostya on 24.05.2017.
 */

public interface IBaseApi<Type>  {
    UnifiedBankResponce mapResponce(Type responceDTO);
    List<UnifiedBankResponce> mapResponceList(List<Type> responceDTO);
}
