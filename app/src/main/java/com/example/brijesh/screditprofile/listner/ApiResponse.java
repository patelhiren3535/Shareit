package com.example.brijesh.screditprofile.listner;

import java.util.ArrayList;

public interface ApiResponse {
    public void apiResponsePostProcessing(String response, int apiCode);

    public void networkError(int apiCode);

    public void responseError(String responseError, int apiCode);

    public void responseError(String responseError, ArrayList<Integer> successedUrlList, int apiCode);
}
