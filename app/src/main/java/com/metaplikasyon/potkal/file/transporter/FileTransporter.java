package com.metaplikasyon.potkal.file.transporter;

import android.content.Context;

// Interface for Copy, Move, Unzip, Zip classes.
public interface FileTransporter {
    boolean transfer(String srcPath, String trgtPath, Context context);
}
