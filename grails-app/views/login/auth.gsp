<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>UBS Login</title>

    <asset:stylesheet src="application.css"/>

    <style>
        body {
            background: #f5f7fa;
        }

        .login-wrapper {
            min-height: 80vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-card {
            width: 100%;
            max-width: 420px;
            border: none;
            border-radius: 16px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.08);
        }

        .ubs-title {
            font-weight: 700;
            letter-spacing: 0.5px;
        }

        
    </style>
</head>

<body>

<div class="container login-wrapper">

    <div class="card login-card">
        <div class="card-body p-4 p-md-5">

            <div class="text-center mb-4">

                <h2 class="ubs-title">
                    UBS Training
                </h2>

                

            </div>

            <g:if test="${flash.message}">
                <div class="alert alert-danger">
                    ${flash.message}
                </div>
            </g:if>

            <form action="${postUrl ?: '/login/authenticate'}"
                  method="POST"
                  autocomplete="off">

                <div class="mb-3">

                    <label for="username"
                           class="form-label">
                        Email
                    </label>

                    <input type="text"
                           class="form-control"
                           id="username"
                           name="username"
                           placeholder="Enter your email"
                           required
                           autofocus>

                </div>

                <div class="mb-3">

                    <label for="password"
                           class="form-label">
                        Password
                    </label>

                    <input type="password"
                           class="form-control"
                           id="password"
                           name="password"
                           placeholder="Enter your password"
                           required>

                </div>

                <div class="d-grid mt-4">

                    <button type="submit"
                            class="btn btn-primary">
                        Login
                    </button>

                </div>

            </form>

        </div>
    </div>

</div>

</body>
</html>