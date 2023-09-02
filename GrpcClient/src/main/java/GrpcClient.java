import com.yrrhelp.grpc.User.APIResponse;
import com.yrrhelp.grpc.User.LoginRequest;
import com.yrrhelp.grpc.UserOuterClass;
import com.yrrhelp.grpc.userGrpc;
import com.yrrhelp.grpc.userGrpc.userBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

	public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
		
		// stubs - generate from proto
		
		userBlockingStub userStub = userGrpc.newBlockingStub(channel); //asynchronous call ,if newStub synchronous

		UserOuterClass user = UserOuterClass.User.newBuilder().setFirstName("Name")
				.setLastName("LAstname")
				.setEmail("name@gmail.com").
				setCourse(UserOuterClass.User.CourseName.newBuilder().setCourseID("101").setCourseName("CS").setCreditScore("10").setHod("HOD").build()).build();

		LoginRequest loginrequest = LoginRequest.newBuilder().setUsername("RAMashgd").setPassword("RAM").build();
		
		APIResponse response = userStub.login(loginrequest);
		
		System.out.println(response.getResponsemessage());
		System.out.println(response.getResponseCode());

		
		
	}

}
