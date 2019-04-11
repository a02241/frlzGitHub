package com.frlz.service;

import com.frlz.pojo.AfterTag;

public interface AfterTagService {
    void addAfterTag(AfterTag afterTag);

    AfterTag selectAfterTagByAfterTag(AfterTag afterTag);

    void updateAfterTag(String message,String afterTagId);
}
