import org.springframework.security.access.AccessDeniedException

class UrlMappings {

   static mappings = {
      "/$controller/$action?/$id?"{
         constraints {}
      }

      "/"(view:"/index")

      "403"(controller: "errors", action: "error403")
      "404"(controller: "errors", action: "error404")
      "500"(controller: "errors", action: "error403",
            exception: AccessDeniedException)
   }
}
