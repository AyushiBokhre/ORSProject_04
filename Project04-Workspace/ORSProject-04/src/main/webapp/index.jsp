<%@ page import="in.co.rays.proj4.controller.ORSView" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Online Result System</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

    <div class="container min-vh-100 d-flex flex-column
                justify-content-center align-items-center py-5">

        <!-- Logo -->
        <div class="bg-white rounded-4 shadow-sm p-4 mb-4 text-center">
            <img src="img/customLogo.jpg"
                 alt="Online Result System Logo"
                 class="img-fluid"
                 width="318"
                 height="127">
        </div>

        <!-- Welcome Card -->
        <div class="card border-0 shadow rounded-4 p-4 p-md-5
                    text-center w-100"
             style="max-width: 750px;">


            <h2 class="h4 text-dark mb-3">
                Online Result System
            </h2>

            <p class="text-secondary mb-4">
               Manage students, colleges, marksheets and academic information easily in one place.
            </p>

            <div>
                <a href="<%=ORSView.WELCOME_CTL%>"
                   class="btn btn-primary btn-lg  px-5 shadow-sm">
                    Get Started &rarr;
                </a>
            </div>

        </div>

        <!-- Footer -->
        <footer class="text-secondary text-center mt-4">
            <small>
                &copy; 2026 Online Result System
            </small>
        </footer>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>

</body>
</html>