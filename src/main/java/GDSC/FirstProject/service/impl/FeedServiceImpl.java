package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.service.FeedService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedServiceImpl implements FeedService {
    @Override
    public String makeRandomS3Key() {
        return UUID.randomUUID().toString();
    }

}