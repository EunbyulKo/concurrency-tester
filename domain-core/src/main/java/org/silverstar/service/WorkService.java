package org.silverstar.service;

import org.springframework.stereotype.Service;

@Service
public class WorkService {

    public void sleep3000() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String calculateLongTime() {
        String text = "";
        for (int i = 0; i < 200000; i++) {
            text += i;
        }

        return text.substring(0, 10);
    }

}
