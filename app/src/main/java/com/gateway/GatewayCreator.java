package com.gateway;

/**
 * A Gateway class that create the corresponding Gateway class.
 */

public class GatewayCreator{


    /**
     * Create a specific Gateway based on the input FileTypy filetype and also construct the new gateway using the
     * filePath string and entityType.
     * @param fileType the type of the input file
     * @param filePath the directory path of the file
     * @return the corresponding type of gateway
     */
    public IGateway createIGateway(FileType fileType, String filePath) {
        if (fileType == FileType.SER){
            return new SerGateway(filePath);
        }
        return null;
    }
}
