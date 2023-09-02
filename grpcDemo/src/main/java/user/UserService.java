package user;


import com.yrrhelp.grpc.UserOuterClass;
import com.yrrhelp.grpc.userGrpc;
import io.grpc.stub.StreamObserver;

public class UserService extends userGrpc.userImplBase {

    @Override
    public void login(UserOuterClass.LoginRequest request, StreamObserver<UserOuterClass.APIResponse> responseObserver) {
        System.out.println("in login page");
        String username = request.getUsername();
        String password = request.getPassword();
        UserOuterClass.APIResponse.Builder response = UserOuterClass.APIResponse.newBuilder();
        if(username.equals(password)){
            response.setResponseCode(0).setResponsemessage("Success");

        }
        else{
            response.setResponseCode(100).setResponsemessage("Invalid login");
        }

        responseObserver.onNext(response.build()); //sending data back to the client
        responseObserver.onCompleted();
    }

    @Override
    public void logout(UserOuterClass.Empty request, StreamObserver<UserOuterClass.APIResponse> responseObserver) {
        super.logout(request, responseObserver);
    }

    @Override
    public void createUser(UserOuterClass.User request, StreamObserver<UserOuterClass.User> responseObserver) {
        System.out.println("Received user JSON: "+ request.toString());
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }
}
