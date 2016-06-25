package com.example.brijesh.screditprofile.Model.ResponseModel;

import java.util.ArrayList;

/**
 * Created by admin-4 on 21/6/16.
 */
public class LanguageResponse {


    public ArrayList<Datum> getLanguage() {
        return Language;
    }

    public void setLanguage(ArrayList<Datum> language) {
        Language = language;
    }

    ArrayList<Datum> Language;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    boolean Status;


    public class Datum {

            String languageid;
            String languagecode;
            String Languagename;

        public String getLanguagename() {
            return Languagename;
        }

        public void setLanguagename(String Languagename) {
            this.Languagename = Languagename;
        }

        public String getLanguageid() {
            return languageid;
        }

        public void setLanguageid(String languageid) {
            this.languageid = languageid;
        }

        public String getLanguagecode() {
            return languagecode;
        }

        public void setLanguagecode(String languagecode) {
            this.languagecode = languagecode;
        }

    }
}
