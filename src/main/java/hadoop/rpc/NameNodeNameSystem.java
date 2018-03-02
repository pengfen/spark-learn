package hadoop.rpc;


public class NameNodeNameSystem implements ClientNameNodeProtocol {

    public String getMetaData(String path) {

        return path+"[blk_01,blk_02,blk_03]\n{blk_01:node01,node02}";

    }


}