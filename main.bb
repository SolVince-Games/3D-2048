Graphics3D 653, 653, 32, 2
SetBuffer BackBuffer()

; Create Camera
camera = CreateCamera()

; Camera position, rotation, and speed
cam_x# = 4.5
cam_y# = 4.5
cam_z# = -8
cam_speed# = 0.01

cam_rot_x# = 0.0
cam_rot_y# = 0.0
cam_rot_speed# = 0.5


; Debug Info
show_keycode = False
toggle_show_keycode = True

show_cam_details = False
toggle_show_cam_details = True


; Cube Grid Dimensions
length = 4
width = 4
depth = 4

; Create light
light = CreateLight(0,camera)

; Create cubes in a 3D grid with 1-unit gaps (cube is 2x2x2, so 3 units apart)
For x = 0 To length - 1
    For y = 0 To width - 1
        For z = 0 To depth - 1
            cube = CreateCube()
            PositionEntity cube, x * 3, y * 3, z * 3
            EntityAlpha cube, 0.7 ; Make it kinda transparent
            EntityColor cube, Rnd(0, 255), Rnd(0, 255), Rnd(0, 255) ; Random color
        Next
    Next
Next

; Main Loop
While Not KeyDown(1)

    ; Position the camera
    PositionEntity camera, cam_x, cam_y, cam_z
    RotateEntity camera, cam_rot_x, cam_rot_y, 0

    ; Render the scene
    RenderWorld

    ; Debug

    ; Print camera position + rotation
    If KeyDown(59) Then ; F1
        If toggle_show_cam_details Then show_cam_details = not show_cam_details
        toggle_show_cam_details = False
    ElseIf not toggle_show_cam_details
        toggle_show_cam_details = True
    EndIf
    If show_cam_details Then
        Text 32, 32+16*0, "Camera:"
        Text 32+8, 32+16*1, "X: " + cam_x + " Y: " + cam_y + " Z: " + cam_z
        Text 32+8, 32+16*2, "Rot X: " + cam_rot_x + " Rot Y: " + cam_rot_y
    EndIf

    ; Print key codes
    If KeyDown(60) Then ; F2
        If toggle_show_keycode Then show_keycode = not show_keycode
        toggle_show_keycode = False
    ElseIf not toggle_show_keycode
        toggle_show_keycode = True
    EndIf

    If show_keycode Then
        Text 32, 96, "Keys pressed:"
        i_pos = 1
        For i = 1 To 255
            If KeyDown(i) Then
                Text 32+8, 96+16*i_pos, i
                i_pos = i_pos + 1
            EndIf
        Next
    EndIf

    ; Reset camera position
    If KeyDown(61) Then ; F3
        cam_x# = 4.5
        cam_y# = 4.5
        cam_z# = -8
    EndIf

    ; Reset camera rotation
    If KeyDown(62) Then ; F4
        cam_rot_x = 0
        cam_rot_y = 0
    EndIf
    ; End Debug

    ; Camera speed adjustment (Hold Ctrl to move faster)
    If KeyDown(29) Then ; Left Ctrl
        cam_speed = 0.05
        cam_rot_speed = 1.0
    Else
        cam_speed = 0.01
        cam_rot_speed = 0.5
    EndIf

    ; WASD + Space/Shift - Camera movement
    If KeyDown(17) Then cam_z = cam_z + cam_speed  ; W
    If KeyDown(31) Then cam_z = cam_z - cam_speed  ; S
    If KeyDown(30) Then cam_x = cam_x - cam_speed  ; A
    If KeyDown(32) Then cam_x = cam_x + cam_speed  ; D
    If KeyDown(57) Then cam_y = cam_y + cam_speed  ; Space
    If KeyDown(42) Then cam_y = cam_y - cam_speed  ; Left Shift

    ; IJKL - Camera rotation
    If KeyDown(23) Then cam_rot_x = cam_rot_x - cam_rot_speed  ; I
    If KeyDown(37) Then cam_rot_x = cam_rot_x + cam_rot_speed  ; K
    If KeyDown(36) Then cam_rot_y = cam_rot_y + cam_rot_speed  ; J
    If KeyDown(38) Then cam_rot_y = cam_rot_y - cam_rot_speed  ; L

    ; Clamp Rotation (x = -90 - 90, y = -180 - 180)
    If cam_rot_x > 90 Then cam_rot_x = 90
    If cam_rot_x < -90 Then cam_rot_x = -90
    If cam_rot_y > 180 Then cam_rot_y = cam_rot_y - 360
    If cam_rot_y < -180 Then cam_rot_y = cam_rot_y + 360

    Flip

Wend End