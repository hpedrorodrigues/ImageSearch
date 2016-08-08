package com.hpedrorodrigues.researcher.constant;

import android.os.Environment;

public interface ISConstant {

    String EMAIL = "hs.pedro.rodrigues@gmail.com";

    String DEFAULT_SEARCH = "tree";

    String DEFAULT_DIRECTORY = Environment.DIRECTORY_DOWNLOADS;

    int IMAGES_PER_PAGE = 15;
    int INITIAL_PAGE = 1;

    boolean DEFAULT_ASK_TO_EXIT = true;
    boolean DEFAULT_KEEP_SCREEN_ON = true;
    boolean DEFAULT_SHOW_IMAGES_DESCRIPTION = true;
    boolean DEFAULT_SAFE_SEARCH = true;
}