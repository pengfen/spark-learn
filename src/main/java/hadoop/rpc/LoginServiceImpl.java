package hadoop.rpc;

public class LoginServiceImpl implements LoginServiceInterface{

    public String login(String username, String password) {

        System.out.println(username + "你总算来了，等死我了");


        return username + "successfully loged in , welcome......";
    }

}
