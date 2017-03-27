package com.example.davidc.uiwrapper;

interface UiWrapperRepositoryProvider<R extends UiWrapperRepository> {
    R get();
}
