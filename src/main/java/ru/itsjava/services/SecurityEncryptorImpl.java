package ru.itsjava.services;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityEncryptorImpl implements SecurityEncryptor{

    @Override
    public String md5Encrypt(String st) {
        String md5Password = DigestUtils.md5Hex(st);
        return md5Password;
    }
}
