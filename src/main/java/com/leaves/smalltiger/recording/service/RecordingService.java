package com.leaves.smalltiger.recording.service;

import com.leaves.smalltiger.common.utils.MsgResult;

public interface RecordingService {
    public MsgResult recordingInfoSelect(int conId);

    public MsgResult dataSelect(int conId);
}
