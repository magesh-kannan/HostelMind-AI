import { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Container,
  Paper,
  Typography,
  TextField,
  Button,
  Box,
  InputAdornment,
  IconButton,
} from "@mui/material";

import {
  Visibility,
  VisibilityOff,
} from "@mui/icons-material";

import { login } from "../../services/authService";

function LoginPage() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async () => {

    try {

      const response = await login(username, password);

      localStorage.setItem("token", response.token);
      localStorage.setItem("username", response.username);
      localStorage.setItem("role", response.role);

      navigate("/dashboard");

      console.log(response);

    } catch (error) {

      alert("Invalid Username or Password");

      console.error(error);

    }

  };

  return (
    <Container maxWidth="sm">

      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          minHeight: "100vh",
        }}
      >

        <Paper
          elevation={6}
          sx={{
            padding: 5,
            width: "100%",
            borderRadius: 3,
          }}
        >

          <Typography
            variant="h4"
            align="center"
            fontWeight="bold"
            gutterBottom
          >
            HostelMind AI
          </Typography>

          <Typography
            align="center"
            color="text.secondary"
            mb={4}
          >
            Admin Login
          </Typography>

          <TextField
            fullWidth
            label="Username"
            margin="normal"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <TextField
            fullWidth
            label="Password"
            margin="normal"
            type={showPassword ? "text" : "password"}
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                  <IconButton
                    onClick={() =>
                      setShowPassword(!showPassword)
                    }
                  >
                    {showPassword
                      ? <VisibilityOff />
                      : <Visibility />}
                  </IconButton>
                </InputAdornment>
              ),
            }}
          />

          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{
              mt: 3,
              py: 1.5,
            }}
            onClick={handleLogin}
          >
            Login
          </Button>

        </Paper>

      </Box>

    </Container>
  );
}

export default LoginPage;