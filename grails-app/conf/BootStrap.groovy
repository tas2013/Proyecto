import com.pugali.*
import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

    def init = { servletContext ->
      def adminRole = Role.findByName("Administrator")
      if(!adminRole){
         adminRole = new Role(name: 'Administrator')
         adminRole.addToPermissions("*:*")
         adminRole.save()
      }

      def admin = User.findByUsername('admin')

      if(!admin){

         admin = new User(firstName:"Administrator",lastName:"User",
            username: 'admin', passwordHash: new Sha256Hash("admin").toHex(),email:'someone@gmail.com')
         admin.save()
         adminRole.addToUsers(admin)
         adminRole.save()
      }
   }

   def destroy = {

   }
}
